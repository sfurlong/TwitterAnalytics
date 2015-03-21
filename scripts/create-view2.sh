HOSTNAME=192.168.1.16

curl -v -X POST  --anyauth --user admin:marklogic1 --header "Content-Type:application/json" -d@./create-view2.json http://$HOSTNAME:8002/manage/v2/databases/SQLdata/view-schemas/main/views?format=json
