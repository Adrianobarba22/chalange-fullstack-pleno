import { Button, Card, Form, Input, Table } from "antd";
import "antd/dist/reset.css";
import React, { useEffect, useState } from "react";
import { Navigate, Route, BrowserRouter as Router, Routes } from "react-router-dom";

const API_URL = "http://localhost:8090";

const Login = ({ setAuth }) => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = () => {
    const authHeader = "Basic " + btoa(`${username}:${password}`);
    sessionStorage.setItem("auth", authHeader);
    setAuth(authHeader);
  };

  return (
    <Card title="Login" style={{ width: 300, margin: "100px auto" }}>
      <Form onFinish={handleLogin}>
        <Form.Item label="Usuário">
          <Input value={username} onChange={(e) => setUsername(e.target.value)} />
        </Form.Item>
        <Form.Item label="Senha">
          <Input.Password value={password} onChange={(e) => setPassword(e.target.value)} />
        </Form.Item>
        <Button type="primary" htmlType="submit" block>Entrar</Button>
      </Form>
    </Card>
  );
};

const Contatos = ({ auth, logout }) => {
  const [contatos, setContatos] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch(`${API_URL}/contato`, { headers: { Authorization: auth } })
      .then((res) => res.json())
      .then((data) => {
        setContatos(data);
        setLoading(false);
      });
  }, [auth]);

  return (
    <Card title="Gerenciador de Contatos" extra={<Button onClick={logout}>Logout</Button>}>
      <Table dataSource={contatos} loading={loading} rowKey="id" columns={[
        { title: "Nome", dataIndex: "name", key: "name" },
        { title: "Email", dataIndex: "email", key: "email" },
        { title: "Telefone", dataIndex: "phone", key: "phone" },
      ]} />
    </Card>
  );
};

const App = () => {
  const [auth, setAuth] = useState(sessionStorage.getItem("auth"));

  const logout = () => {
    sessionStorage.removeItem("auth");
    setAuth(null);
  };

  return (
    <Router>
      <Routes>
        <Route path="/login" element={!auth ? <Login setAuth={setAuth} /> : <Navigate to="/" />} />
        <Route path="/" element={auth ? <Contatos auth={auth} logout={logout} /> : <Navigate to="/login" />} />
      </Routes>
    </Router>
  );
};

export default App;