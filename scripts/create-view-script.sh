
# Create the schema for the View to be used by ODBC
curl -X POST --anyauth --user admin:marklogic1 \
--header "Content-Type:application/json" -d@"./create-view.json" \
http://localhost:8002/manage/v2/databases/tweetdeck/view-schemas/main/views?format=json