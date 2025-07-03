#!/bin/bash

echo "Testing App Extractor API..."
echo "=============================="

# Test the extract endpoint
echo "POST /api/extract"
echo "Request Body:"
cat << 'EOF'
[
    "app_aim_ecs_gcg_dev_158298",
    "app_aim_ecs_gcg_prod_158298",
    "app_aim_ecs_gcg_uat_169284",
    "app_aim_ecs_gcg_uat_168988",
    "app_aim_ecs_gcg_dev_169284",
    "app_aim_ecs_gcg_dev_168988"
]
EOF

echo ""
echo "Response:"
curl -X POST http://localhost:8080/api/extract \
  -H "Content-Type: application/json" \
  -d '[
    "app_aim_ecs_gcg_dev_158298",
    "app_aim_ecs_gcg_prod_158298",
    "app_aim_ecs_gcg_uat_169284",
    "app_aim_ecs_gcg_uat_168988",
    "app_aim_ecs_gcg_dev_169284",
    "app_aim_ecs_gcg_dev_168988"
  ]' | jq '.'

echo ""
echo "=============================="
echo "Health Check:"
curl -X GET http://localhost:8080/api/health
echo "" 