echo "This is afterInstall.sh!!!"
cd /home/environment/onePosPay/target
pwd
ls
echo "docker build!!!"
docker build -t onepos-pay-backend:latest .
docker tag onepos-pay-backend:latest 648568805009.dkr.ecr.ap-northeast-2.amazonaws.com/onepospay

docker images
docker push 648568805009.dkr.ecr.ap-northeast-2.amazonaws.com/onepospay:latest
aws eks update-kubeconfig --region ap-northeast-2 --name eks-kitchen-cluster

echo "kubectl install!!!"
pwd
ls

#sudo install -o root -g root -m 0755 ./ /home/environment/onePosPay/target

echo "kubectl apply!!!"
#sed -i.bak 648568805009.dkr.ecr.ap-northeast-2.amazonaws.com/onepospay deployment.yml
kubectl apply -f deployment.yaml
kubectl apply -f service.yaml

#kubectl apply -f ./ingress.yml
