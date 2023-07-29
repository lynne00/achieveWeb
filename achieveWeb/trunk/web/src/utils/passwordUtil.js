import { sha512 } from 'js-sha512';

// 使用 SHA-512 对密码进行哈希处理
const hashPassword = (password) => {
  return sha512(password);
};

export { hashPassword };