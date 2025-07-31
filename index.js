// index.js
const express = require("express");
const bodyParser = require("body-parser");
const { register, login } = require("./users");

const app = express();
app.use(bodyParser.json());

/**
 * Ruta para registrar nuevos usuarios
 */
app.post("/register", async (req, res) => {
  const { username, password } = req.body;
  try {
    const message = await register(username, password);
    res.status(201).json({ message });
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
});

/**
 * Ruta para iniciar sesión
 */
app.post("/login", async (req, res) => {
  const { username, password } = req.body;
  try {
    const message = await login(username, password);
    res.status(200).json({ message });
  } catch (error) {
    res.status(401).json({ error: error.message });
  }
});

// Puerto del servidor
app.listen(3000, () => {
  console.log("Servidor corriendo en http://localhost:3000");
});
