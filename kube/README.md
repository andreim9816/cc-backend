# How to deploy to kubernetes

1. The deployment is done on the Google Cloud Platform. The Kubernetes cluster can be accessed here (make sure you have access to the cluster):

```
https://console.cloud.google.com/kubernetes/clusters/details/us-central1/autopilot-cluster-1/details?orgonly=true&project=cosmic-mariner-373919&supportedpurview=organizationId
```

2. To connect to the cluster, in your Google Cloud SDK Shell enter the following command:
```
gcloud container clusters get-credentials autopilot-cluster-1 --region us-central1 --project cosmic-mariner-373919
```

3. To deploy, you may want to first delete the deployments:
```
kubectl delete deployment --all
```

4. Apply the configmap:
```
kubectl apply -f configmap.yml
```

5. Apply the secrets:
```
kubectl apply -f secret-banking-api.yml
kubectl apply -f secret-payment-api.yml
kubectl apply -f secret-rabbitmq.yml
```


6. Apply the deployments:
```
kubectl apply -f deployment-frontend.yml
kubectl apply -f deployment-banking-api.yml
kubectl apply -f deployment-rabbitmq.yml
kubectl apply -f deployment-payment-api.yml
```

7. To check that everything went good, you can see the status of the pods:
```
kubectl get pods
```
Also, you can check the events for a specific pod/deployment
```
kubectl describe pod <POD_NAME>
kubectl describe deployment <DEPLOYMENT_NAME>
```
Or see the logs of the pod
```
kubectl logs <POD_NAME> 
```
