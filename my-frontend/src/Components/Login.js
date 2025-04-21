import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { Container, Grid, Typography, TextField, Button } from '@mui/material';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');

    try {
      const response = await axios.post('http://localhost:8080/api/auth/login', {
        email,
        password,
      });
      console.log(response);
      if (response.data.role === 'admin') {
        localStorage.setItem('role', 'admin');
        navigate('/admin-dashboard', { replace: true });
      } else if (response.data.role === 'user') {
        localStorage.setItem('role', 'user');
        navigate('/client-dashboard', { replace: true });
      }
    } catch (err) {
      setError('Login failed. Please check your email and password.');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container maxWidth="sm">
      <Grid container spacing={4} direction="row" alignItems="center" justifyContent="center" sx={{ mt: 8 }}>
        <Grid item xs={12} sm={8} md={6}>
          <Typography variant="h4" component="h1" gutterBottom>
            Login
          </Typography>
          {error && <Typography variant="error" gutterBottom>{error}</Typography>}
          <form onSubmit={handleSubmit}>
            <TextField
              label="Email"
              variant="outlined"
              fullWidth
              margin="normal"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
            <TextField
              label="Password"
              variant="outlined"
              fullWidth
              margin="normal"
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
            <Button
              type="submit"
              variant="contained"
              color="primary"
              fullWidth
              disabled={loading}
            >
              {loading ? 'Logging in...' : 'Login'}
            </Button>
          </form>
        </Grid>
      </Grid>
    </Container>
  );
};

export default Login;
