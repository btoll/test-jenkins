String call(String mule_version = "latest", String name, String arch = "amd64") {
    def cpu = "3000m"
    def memory = "6144M"

    return """
apiVersion: v1
kind: Pod
metadata:
  name: ${name}-${arch}
spec:
  nodeSelector:
    type: worker
  containers:
  - name: mule
    image: algorand/mule:${mule_version}
    command: [ "sleep", "21600" ]
    env:
    - name: DOCKER_HOST
      value: tcp://localhost:2376
    - name: DOCKER_TLS_VERIFY
      value: '1'
    volumeMounts:
    - name: dockerd-certs
      mountPath: /root/.docker/
    resources:
      limits:
        memory: 500Mi
        cpu: 256m
  - name: dind-daemon
    image: docker:19-dind
    env:
    - name: DOCKER_TLS_CERTDIR
      value: /certs
    resources:
      requests:
        cpu: ${cpu}
        memory: ${memory}
    securityContext:
      privileged: true
    volumeMounts:
    - name: dockerd-certs
      mountPath: /certs/client
    - name: binfmt-onboot
      mountPath: /containers/onboot/002-binfmt
      readOnly: true
    securityContext:
      privileged: true
  volumes:
  - name: binfmt-onboot
    hostPath:
      path: /containers/onboot/002-binfmt
  - name: dockerd-certs
    emptyDir: {}
"""
}

