##Пример запуска приложения в minikube

собрать проект (clean install)

собрать и опубликовать docker-образ 
docker build .
docker tag <image hash> exit
docker login
docker push javapipe2020/testservice:ssl

запустить minikube
minikube start
minikube ip (вернет ip-адрес виртуального узла с k8s, например 172.17.0.2)
minikube dashboard

задеплоить конфигурацию
kubectl apply -f test-service-configmap.yaml

создать secret
cd src/main/resources/keystore/local/
kubectl create secret generic testservicesecret --from-literal=keystore_password=123456 --from-file=keystore.p12=keystore.p12 --from-literal=truststore_password=123456 --from-file=truststore.jks=truststore.jks

создать deployment
cd ../../../../..
kubectl apply -f test-service-deployment.yaml

создать service
kubectl apply -f test-service-service.yaml

проверить работоспособность из Postman, прописав настройки сертификатов из папки src/main/resources/keystore/local/client passphrase 1234567890
(должно вернуть 200 [])
GET https://172.17.0.2:31007/person/