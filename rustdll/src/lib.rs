// src/lib.rs
use std::ffi::{CStr, CString};
use std::os::raw::c_char;

#[no_mangle]
pub extern "C" fn addNumbers(a: i32, b: i32) -> i32 {
    a + b
}

#[no_mangle]
pub extern "C" fn processString(input: *const c_char) -> *mut c_char {
    let c_str = unsafe {
        assert!(!input.is_null());
        CStr::from_ptr(input)
    };

    // 尝试解析为 UTF-8，如果失败返回默认错误消息
    let input_str = match c_str.to_str() {
        Ok(s) => s,
        Err(_) => {
            let err_message = "Invalid UTF-8 input";
            return CString::new(err_message).unwrap().into_raw();
        }
    };

    // 处理字符串逻辑
    let output_str = format!("你好啊, {}!", input_str);

    // 转换为 C 字符串并返回指针
    CString::new(output_str).unwrap().into_raw()
}

#[no_mangle]
pub extern "C" fn greet(name: *const c_char) -> *mut c_char {
    let c_str = unsafe {
        assert!(!name.is_null());
        CStr::from_ptr(name)
    };

    let input_str = match c_str.to_str() {
        Ok(s) => s,
        Err(_) => return CString::new("Invalid UTF-8 input").unwrap().into_raw()
    };

    let output = format!("Hello调用rustgreet方法, {}!", input_str);
    CString::new(output).unwrap().into_raw()
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn it_works() {
        let result = addNumbers(2, 2);
        println!("计算结果:{}", result);
        assert_eq!(result, 4);
    }

    #[test]
    fn test_process_string() {
        let input = CString::new("Hello, world!").unwrap();
        let result = unsafe {
            let raw_result = greet(input.as_ptr());
            CStr::from_ptr(raw_result).to_string_lossy().into_owned()
        };
        assert_eq!(result, "Hello调用rustgreet方法, Hello, world!!");
        // assert_eq!(result, "Hello调用rustgreet方法, Hello, world!!2");
    }


}
