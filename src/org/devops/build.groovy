package org.devops

// 构建类型

// 定义一个buildTools的map
def buildTools = ["mvn":"M2","ant":"ANT","gradle":"GRADLE","npm":"NPM"]

def Build(buildType,buildShell){
  println("当前选择的构建类型是${buildType}")
  // 获取buildHome
  buildHome = tool buildTools[buildType]
  
  // 执行打包
  sh "${buildHome}/bin/${buildType} ${buildShell}"
}
