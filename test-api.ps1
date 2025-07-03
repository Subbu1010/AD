# Test App Extractor API
Write-Host "Testing App Extractor API..." -ForegroundColor Green
Write-Host "==============================" -ForegroundColor Green

# Test the extract endpoint
Write-Host "POST /api/extract" -ForegroundColor Yellow
Write-Host "Request Body:" -ForegroundColor Yellow
$requestBody = @"
[
    "app_aim_ecs_gcg_dev_158298",
    "app_aim_ecs_gcg_prod_158298",
    "app_aim_ecs_gcg_uat_169284",
    "app_aim_ecs_gcg_uat_168988",
    "app_aim_ecs_gcg_dev_169284",
    "app_aim_ecs_gcg_dev_168988"
]
"@

Write-Host $requestBody -ForegroundColor Gray

Write-Host ""
Write-Host "Response:" -ForegroundColor Yellow

try {
    $response = Invoke-WebRequest -Uri "http://localhost:8080/api/extract" -Method POST -ContentType "application/json" -Body $requestBody
    $response.Content | ConvertFrom-Json | ConvertTo-Json -Depth 10
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

Write-Host ""
Write-Host "==============================" -ForegroundColor Green
Write-Host "Health Check:" -ForegroundColor Yellow
try {
    $healthResponse = Invoke-WebRequest -Uri "http://localhost:8080/api/health" -Method GET
    Write-Host $healthResponse.Content -ForegroundColor Green
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

Write-Host "" 