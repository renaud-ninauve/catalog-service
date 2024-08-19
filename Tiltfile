# Build
ref = 'catalog-service'
custom_build(
 # Name of the container image
 ref = 'catalog-service',
 # Command to build the container image
 command = 'mvn spring-boot:build-image -Dspring-boot.build-image.imageName=$EXPECTED_REF',
 # Files to watch that trigger a new build
 deps = ['pom.xml', 'src']
)

# Deploy
allow_k8s_contexts('polar')
k8s_yaml(['k8s/deployment.yml', 'k8s/service.yml'])

# Manage
k8s_resource('catalog-service', port_forwards=['9001'])

