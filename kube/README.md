# How to deploy to kubernetes

1. The deployment is done on the Google Cloud Platform. The Kubernetes cluster can be accessed here (make sure you have access to the cluster):

```
https://console.cloud.google.com/kubernetes/clusters/details/us-central1/autopilot-cluster-1/details?orgonly=true&project=cosmic-mariner-373919&supportedpurview=organizationId
```

2. To connect to the cluster, in you Google Cloud SDK Shell enter the following command:
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
```


6. Then apply the deployments:
```
kubectl apply -f deployment-frontend.yml
kubectl apply -f deployment-banking-api.yml
```
