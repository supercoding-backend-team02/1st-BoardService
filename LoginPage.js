import React, { useState } from "react";
import { TextField } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { blue, CustomButton } from "./CustomButton";

const LoginPage = () => {
  const navigate = useNavigate();
  const [loginState, setLoginState] = useState({
    email: "",
    password: "",
  });

  //...

  const loginHandler = async (path, email, password) => {
    try {
      const response = await fetch(`http://localhost:8080${path}`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          email,
          password,
        }),
      });

      if (response.ok) {
        const data = await response.json();

        if (path === "/api/login") {
          // 로그인 성공 시 필요한 작업 수행
          localStorage.setItem("email", email);
          navigate("/");
        } else {
          // 회원가입 성공 시 필요한 작업 수행
          // 예: 회원가입 후 로그인 페이지로 이동
          navigate("/api/login");
        }
      } else {
        // 서버 응답이 실패한 경우
        console.error("Login failed");

        // 여기서 response.text()를 사용하여 서버의 응답을 확인할 수 있습니다.
        const errorMessage = await response.text();
        console.error(errorMessage);
      }
    } catch (error) {
      console.error("Error during login:", error);
    }
  };

  //...

  return (
    <div
      style={{
        padding: "40px",
      }}
    >
      <h1>로그인 페이지</h1>
      <h2>이메일</h2>
      <TextField
        id="outlined-basic"
        label="이메일을 입력해주세요."
        variant="outlined"
        onChange={(event) =>
          setLoginState((prev) => ({ ...prev, email: event.target.value }))
        }
      />
      <h2>비밀번호</h2>
      <TextField
        id="outlined-basic"
        label="비밀번호를 입력해주세요."
        variant="outlined"
        onChange={(event) =>
          setLoginState((prev) => ({ ...prev, password: event.target.value }))
        }
      />
      <div
        style={{
          marginTop: "20px",
        }}
      >
        <CustomButton
          style={{ backgroundColor: blue[900] }}
          onClick={() =>
            loginHandler("/api/signup", loginState.email, loginState.password)
          }
        >
          회원가입
        </CustomButton>
        <CustomButton
          style={{ backgroundColor: blue[500] }}
          onClick={() =>
            loginHandler("/api/login", loginState.email, loginState.password)
          }
        >
          로그인
        </CustomButton>
      </div>
    </div>
  );
};

export default LoginPage;
