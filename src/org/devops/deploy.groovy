package org.devops

// 执行部署

// 使用ansible部署
def AnsibleDeploy(hosts,command){
  sh "ansible ${hosts} ${command}"
}
