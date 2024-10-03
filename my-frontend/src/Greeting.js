import React, { useEffect, useState } from 'react';
import axios from 'axios';

const Greeting = () => {
    const [message, setMessage] = useState('');
    useEffect(() => {
        const fetchGreeting = async () => {
            try {
                const response = await axios.get(`${process.env.REACT_APP_API_URL}`);
                setMessage(response.data);
            } catch (error) {
                console.error('Error fetching greeting:', error);
            }
        };
        fetchGreeting();
    }, []);

    return (
        <div>
            <h1>{message}</h1>
        </div>
    );
};
export default Greeting