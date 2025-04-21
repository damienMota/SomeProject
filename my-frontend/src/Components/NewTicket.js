import React, { useState } from 'react';
import { TextField, Button, Container, Box } from '@mui/material';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import dayjs from 'dayjs';
import axios from 'axios';

const NewTicket = () => {
  const [form, setForm] = useState({
    name: '',
    description: '',
    date: null,
  });
  const handleSubmit = async (e) => {
    e.preventDefault();
        try {
          const response = await axios.post('http://localhost:8080/api/newticket/create', {
            name: form.name,
            description: form.description,
            date: form.date
            });
          console.log('Ticket created:', response.data);
          // Handle success, e.g., show a success message
        } catch (error) {
          console.error('Error creating ticket:', error);
          // Handle error, e.g., show an error message
        } finally {
            console.log("Ticket created");
        }
  };
  const today = dayjs(); // Get today's date

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleDateChange = (newDate) => {
    if (newDate && newDate.isBefore(today, 'day')) {
      return; // Prevent selecting past dates
    }
    setForm({ ...form, date: newDate });
  };

  const isFormValid = form.name.trim() !== '' && form.description.trim() !== '' && form.date !== null;

  return (
    <Container maxWidth="sm">
      <Box sx={{ padding: 2, backgroundColor: '#fff', borderRadius: 2 }}>
        <h1>Create New Ticket</h1>
        <form onSubmit={handleSubmit}>
          <TextField
            label="Name"
            name="name"
            variant="outlined"
            fullWidth
            sx={{ mb: 2 }}
            value={form.name}
            onChange={handleChange}
          />

          <LocalizationProvider dateAdapter={AdapterDayjs}>
            <DatePicker
              label="Select a date"
              value={form.date}
              onChange={handleDateChange}
              minDate={today} // Prevents selecting past dates
              textField={(params) => <TextField {...params} fullWidth />}
            />
          </LocalizationProvider>

          <TextField
            label="Description"
            name="description"
            multiline
            rows={4}
            variant="outlined"
            fullWidth
            sx={{ my: 2 }}
            value={form.description}
            onChange={handleChange}
          />

          <Button variant="contained" color="primary" type="submit" disabled={!isFormValid}>
            Submit
          </Button>
        </form>
      </Box>
    </Container>
  );
}

export default NewTicket;

