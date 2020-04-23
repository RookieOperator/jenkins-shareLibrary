//String workspace "/opt/jenkins/workspace"
// 配置共享库
@Library('shareLib')

// 引用共享库中的方法
def tools = new org.devops.tools()
def build = new org.devops.build()

// 定义变量
String buildType = ${env.buildType}
String buildShell = ${env.buildShell}

// Pipeline
pipeline {
	// 指定在哪个节点上执行pipeline
	agent any
	//agent{
	//	node{
	//		label "master"	// 指定运行节点的标签或者名称
	//		customWorkspace "${workspace}"	// 指定运行的工作目录（可选）
	//	}
	//}

	// 设置环境变量
	environment{
		auth = 'joker'
	}

	// 设置参数
	parameters {
		// 字符串类型参数，使用的时候：${params.ENV}
		string(name: 'ENV', defaultValue: 'PRE', description: '预发环境')
		// 布尔类型参数
		booleanParam(name: 'DEBUG_BUILD', defaultValue: true, description: '')
	}

	// 获取自动安装或者手动安装的环境变量
	//tools {
	//	maven "M3"
	//}


	// 指定运行的选项（可选）
	options {
		timestamps()	// 日志会有时间
		skipDefaultCheckout()	// 删除隐式checkout scm语句
		disableConcurrentBuilds()	//禁止并行
		timeout(time:1,unit:'HOURS') //设置流水线超时时间
	}

	// 构建阶段
	stages {
		// 下载代码
		stage("GetCode"){
			// 步骤
			steps{
				// 设置步骤超时时间
				timeout(time:5,unit:'MINUTES'){
					script{
						// println("获取代码")
						tools.PrintMes("获取代码",'red')
					}
				}
			}
		}
		stage("Build"){
			steps{
				timeout(time:20,unit:'MINUTES'){
					script{
						// println("代码打包")
						tools.PrintMes("代码打包",'blue')
						build.Build(buildType,buildShell)
					}
				}
			}
		}
		stage("CodeScanner"){
			steps{
				timeout(time:30,unit:'MINUTES'){
					script{
						// println("代码扫描")
						tools.PrintMes("代码扫描",'green')
					}
				}
			}
		}
	}

	// 构建后的操作
	post {
		always {
			script{
				println("always:不论构建成功与否都会执行")
			}
		}
		success {
			script{
				println("success:只有构建成功才会执行")
				currentBuild.description = "\n构建成功！"
			}
		}
		failure {
			script{
				println("failure:只有构建失败才会执行")
				currentBuild.description = "\n构建失败!"
			}
		}
		aborted {
			script{
				println("aborted:只有取消构建才会执行")
				currentBuild.description = "\n构建取消!"
			}
		}
	}
}
