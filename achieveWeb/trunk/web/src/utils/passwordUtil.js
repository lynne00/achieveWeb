import { SHA512 } from 'crypto-js';

// 使用 SHA-512 对密码进行哈希处理
const hashPassword = (password) => {
  return SHA512(password).toString()
};

export { hashPassword };