#Start minikube
minikube start --mount-string ${PWD}/promtail/:/etc/promtail/ --mount
#Apply configs
# kubectl apply --namespace medok -f medok-database-deployment.yaml,medok-database-service.yaml,medok-deployment.yaml,medok-network-networkpolicy.yaml,medok-service.yaml,medok-vol-persistentvolumeclaim.yaml,promtail-claim1-persistentvolumeclaim.yaml,promtail-deployment.yaml
kubectl apply --namespace medok -f ./kube_configs
#Open dashboard to background
minikube dashboard &
#Access to app
minikube tunnel
