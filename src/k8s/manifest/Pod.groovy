package k8s.manifest

public class Pod implements Serializable {
    private String name
    private String cpu = "2000m"
    private String memory = "1024M"

    public String getManifest(arch = "amd64") {
            return """
apiVersion: v1
kind: Pod
metadata:
  name: ${this.name}-${arch}
spec:
  nodeSelector:
    type: worker
  containers:
  - name: ${this.name}-${arch}
    image: btoll/${this.name}_${arch}:latest
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
        cpu: ${this.cpu}
        memory: ${this.memory}
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
}

