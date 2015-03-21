HOSTNAME=192.168.1.16

# THIS SCRIPT NEEDS WORK...MAY NOT EVEN BE A VALID API
curl -X DELETE --anyauth --user admin:marklogic1 --header "Content-Type:application/json" -d '{"view-schema-name": "main"}' http://$HOSTNAME:8002/manage/v2/databases/SQLdata/view-schemas?format=json
