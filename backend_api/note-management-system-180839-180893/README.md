# note-management-system-180839-180893

## Backend API (Spring Boot)

- Runs on port 3001 (configured in `backend_api/src/main/resources/application.properties`).
- Health endpoints:
  - GET `/health` -> "OK" (simple)
  - GET `/actuator/health` -> JSON with status "UP" (actuator)
- API:
  - GET `/` -> welcome text
  - GET `/api/notes` -> list notes
  - Swagger UI: GET `/docs` (redirects to `/swagger-ui.html`), API docs at `/api-docs`
- Database: In-memory H2 (see `application.properties`), console at `/h2-console`

### Running locally

```sh
cd backend_api
./gradlew bootRun
```

Ensure no other process is listening on port 3001 before starting.

### Troubleshooting 502 / Port already in use

If you see "Web server failed to start. Port 3001 was already in use":

1. Check what's listening:
   - `lsof -i :3001 -sTCP:LISTEN -P -n`
2. Stop the old process or start only one instance.
3. Verify health:
   - `curl -i http://localhost:3001/health`
   - `curl -i http://localhost:3001/actuator/health`
4. Check logs by running:
   - `./gradlew bootRun` (ensure no other process is bound to 3001)
