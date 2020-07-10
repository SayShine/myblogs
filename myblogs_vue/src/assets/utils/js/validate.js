//数字验证
const isInteger = (rule, value, callback) => {
  const number = /^[1-9]+[0-9]*$/;
  if (!value || !number.test(value)) {
    callback(new Error('请输入数字'))
  } else {
    callback()
  }
}
//密码非空验证
const passwordnotnull = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请输入密码'))
    console.log("输入为空");
  } else {
    callback()
  }
}


export default {
  isInteger,
  passwordnotnull
}
