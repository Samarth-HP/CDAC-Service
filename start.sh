#!/bin/bash

curl -O https://mgov.gov.in/Resources/TechnicalArticles/msdgweb-mgov-gov-in.crt

eval "keytool -noprompt -storepass changeit -import -alias cdackey -keystore /usr/local/openjdk-11/lib/security/cacerts -file /home/app/msdgweb-mgov-gov-in.crt"

echo "Added certificate"

java -jar app.jar
