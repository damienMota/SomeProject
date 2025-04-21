import React from 'react';
import { Button } from '@mui/material';
import { Link } from 'react-router-dom';

function AdminDashboard() {
  return (
    <div>
      <Link to="/new-ticket">
        <Button variant="contained" color="primary">
          Create Ticket
        </Button>
      </Link>
      <Link to="/tickets">
        <Button variant="contained" color="secondary">
          Ticket
        </Button>
      </Link>
    </div>
  );
}

export default AdminDashboard;