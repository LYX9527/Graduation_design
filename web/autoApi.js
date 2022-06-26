const fs = require('fs')
const path = require('path')
const parse = require('swagger-parser')//记得npm i
//导入api对应的模块名字
const { FILE_NAME, FILE_PATH, SWAGGER_API_PATH } = require('./autoApi.config')
const swaggerUrl = SWAGGER_API_PATH
// api接口方法存放目录
const API_PATH = path.resolve(__dirname, `${FILE_PATH}`)
// 判断目录是否存在
const isExist = (lastPath = '') => {
  const privatePath = `${lastPath ? API_PATH + '/' + lastPath : API_PATH}`
  const stat = fs.existsSync(privatePath)
  if (!stat) {
    fs.mkdirSync(privatePath)
  }
}

// 整理出相同模块路径
const getModules = (map) => {
  map.forEach((value, key) => {
    if (key == FILE_NAME) {
      writeFileApi(key, value)
    }
  })
}

// 写入js
const writeFileApi = (fileName, apiData) => {
  // api.js
  let tplApi = '//路径定义\nexport default {\n'
  // index.js
  let tplIndex = 'import request from \'@/utils/request\'\nimport api from \'./api\'\n\n'

  const apiDataLen = apiData.length
  for (let i = 0; i < apiDataLen; i++) {
    const item = apiData[i]
    const itemKeys = Object.keys(item)	// 请求方法
    const itemTagKey = itemKeys[0]	// 方法数据信息
    const itemKeysFirest = item[itemTagKey]
    const pathName = itemKeysFirest.operationId
    const allPath = item.allPath
    const dataList = ['post', 'POST', 'put', 'PUT', 'delete', 'DELETE']
    const identifier = dataList.includes(itemTagKey) ? 'data' : 'params'
    tplApi = `${tplApi}   ${pathName}: \`${allPath}\`,\n`
    tplIndex = `${tplIndex}\n//${itemKeysFirest.summary}\n` +
      `export const ${pathName} = (${identifier}) => {\n` +
      `  return request({\n` +
      `    url: api.${pathName},\n` +
      `    method: '${itemTagKey}',\n` +
      `    ${identifier}\n` +
      `  })\n` +
      '}'
  }
  tplApi = tplApi + '\n}'
  fs.writeFileSync(`${API_PATH}/${fileName}/api.js`, tplApi)
  fs.writeFileSync(`${API_PATH}/${fileName}/index.js`, tplIndex)
}

// 入口方法
const autoApi = async() => {
  isExist()
  try {
    const parsed = await parse.parse(swaggerUrl)
    const paths = parsed.paths
    const pathsKeys = Object.keys(paths)	// 获取url路径
    const pathsKeysLen = pathsKeys.length
    const modulesMap = new Map()
    for (let i = 0; i < pathsKeysLen; i++) {
      const item = pathsKeys[i]
      const itemAry = item.split('/')
      const pathsItem = paths[item]
      let fileName = itemAry[1]
      if (!fileName) continue
      fileName = fileName.toLowerCase()
      if (fileName == FILE_NAME) {
        // 创建模块目录
        isExist(fileName)
        // 完整路径
        pathsItem.allPath = item
        if (modulesMap.has(fileName)) {
          const fileNameAry = modulesMap.get(fileName)
          fileNameAry.push(pathsItem)
          modulesMap.set(fileName, fileNameAry)
        } else {
          modulesMap.set(fileName, [pathsItem])
        }
      }

    }
    getModules(modulesMap)
  } catch (e) {
    console.log(e)
  }
}

autoApi()

