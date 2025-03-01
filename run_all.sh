#!/bin/bash

set -a
source .env-build
set +a

docker-compose up postgres -d

cd Bibliotecas/file-service-library
mvn clean install
cd ..
cd ..

SERVICES=("api-gateway" "comment-sv" "eureka-sv" "requirement-sv" "traceability-sv" "type-sv" "user-sv")

for service in "${SERVICES[@]}"; do
  echo "Compilando servicio: $service"

   case "$service" in
      "comment-sv")
        export DB_NAME="comment_db"
        ;;
      "requirement-sv")
        export DB_NAME="requirement_db"
        ;;
      "traceability-sv")
        export DB_NAME="traceability_db"
        ;;
      "type-sv")
        export DB_NAME="type_db"
        ;;
      "user-sv")
        export DB_NAME="user_db"
        ;;
      *)
        unset DB_NAME  # Para servicios que no necesitan DB_NAME
        ;;
    esac

  if [ -d "$service" ]; then
    cd "$service" || { echo "No se pudo acceder al directorio $service"; exit 1; }
    mvn clean package || { echo "Error compilando $service"; exit 1; }
    cd ..
  else
    echo "El directorio $service no existe"
  fi
done

./insert_data.sh

docker-compose down

set -a
source .env
set +a


docker-compose up -d

echo "Esperamos unos segundos para que MinIO esté operativo..."
sleep 10

echo "Creando buckets en MinIO..."
docker-compose exec minio mc alias set local http://127.0.0.1:9000 "$MINIO_ACCESS_KEY" "$MINIO_SECRET_KEY"
docker-compose exec minio mc mb local/comments
docker-compose exec minio mc mb local/requirements

echo "Buckets creados."

echo "Todos los servicios han sido compilados exitosamente."