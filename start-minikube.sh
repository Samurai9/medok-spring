#Start minikube
minikube start --mount-string ${PWD}/configs/:/configs/ --mount
#Create namespace
kubectl create namespace medok
#Apply configs
kubectl apply --namespace medok -f ./configs/kube_configs
#Open dashboard to background
minikube dashboard &
#Access to app
minikube tunnel
