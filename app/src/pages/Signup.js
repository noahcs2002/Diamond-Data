import React, { useState } from 'react';
import '../styles/Signup.scss';
import { useNavigate } from 'react-router-dom';

function Signup() {
    const [formData, setFormData] = useState({
        username: '',
        email: '',
        password: '',
        confirmPassword: ''
    });
    const nav = useNavigate();

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        console.log('Form submitted:', formData);

        const endpointUrl = 'http://localhost:8080/diamond-data/api/auth/signup';
        const url = new URL(endpointUrl);
        await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData),
        })
        .then(response => response.json())
        .then(data => {
            if (data) {
                nav('/home');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
    };

    const handleLogin = () => {
        console.log('Navigate to login page');
        // Replace '/login' with the actual path to login page
        nav('/login');
    };

    return (
        <div className="signup-page">
            <div className="title-container">
                <h1 className="title">
                    Diamond Data
                </h1>
            </div>

            <div className="signup-container">
                <h2>Sign Up</h2>
                <form className="signupForm" onSubmit={handleSubmit}>
                    <label>Username:
                        <input
                            type="text"
                            name="username"
                            value={formData.username}
                            onChange={handleChange}
                            required
                        />
                    </label>
                    <label>Email:
                        <input
                            type="email"
                            name="email"
                            value={formData.email}
                            onChange={handleChange}
                            required
                        />
                    </label>
                    <label>Password:
                        <input
                            type="password"
                            name="password"
                            value={formData.password}
                            onChange={handleChange}
                            required
                        />
                    </label>
                    <label>Confirm Password:
                        <input
                            type="password"
                            name="confirmPassword"
                            value={formData.confirmPassword}
                            onChange={handleChange}
                            required
                        />
                    </label>
                    <button type="submit">Sign Up</button>
                    <p>Already have an account? <a onClick={handleLogin}>Log in</a></p>
                </form>
            </div>

            <footer>
                &copy; 2024 diamonddata.com
            </footer>
        </div>
    );
}

export default Signup;
