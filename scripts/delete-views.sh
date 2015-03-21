HOSTNAME=192.168.1.16

curl -X DELETE --anyauth --user admin:marklogic1 --header "Content-Type:application/json" http://$HOSTNAME:8002/manage/v2/databases/SQLschemas/view-schemas/main/views/employees?format=json

curl -X DELETE --anyauth --user admin:marklogic1 --header "Content-Type:application/json" http://$HOSTNAME:8002/manage/v2/databases/SQLschemas/view-schemas/main/views/expenses?format=json

