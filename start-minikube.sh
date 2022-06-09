#Start minikube
minikube start --mount-string ${PWD}/configs/promtail/:/etc/promtail/ --mount
#Apply configs
kubectl apply --namespace medok -f ./configs/kube_configs
#Open dashboard to background
minikube dashboard &
#Access to app
minikube tunnel
