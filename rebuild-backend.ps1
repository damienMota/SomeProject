# Stop and remove the backend container
docker stop backend
docker rm backend

# Rebuild and start just the backend
docker compose up --build backend -d

Write-Host "Backend rebuilt and restarted. Changes should now be visible."
