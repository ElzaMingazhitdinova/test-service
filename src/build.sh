#!/bin/bash
mvn clean
mvn package
docker build --tag "$1" .
docker login -u="$2" -p="$3"
docker push "$1"

kubectl apply -f test-service-configmap.yaml
cd src/main/resources/keystore/local/
kubectl create secret generic testservicesecret --from-literal=keystore_password=1234567890 --from-file=keystore.p12=keystore.p12 --from-literal=truststore_password=1234567890 --from-file=truststore.jks=truststore.jks

cd ../../../../..
sed "s%javapipe2020/testservice:ssl%$1%g" test-service-deployment.yaml > deployment.yaml
kubectl apply -f deployment.yaml

kubectl apply -f test-service-service.yaml