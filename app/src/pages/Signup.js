import React, { useState } from 'react';
import '../styles/Signup.scss';
import { useNavigate } from 'react-router-dom';

function Signup() {
    const [formData, setFormData] = useState({
        username: '',
        email: '',
        password: '',
        confirmPassword: '',
        teamName: '',
        name: '',
        phoneNumber: ''
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
        let body = {};
        body.email = formData.email;
        body.password = formData.password;
        body.name = formData.name;
        body.phoneNumber = formData.phoneNumber || '';

        if (formData.password !== formData.confirmPassword) {
            alert('Passwords do not match')
            return;
        }

        if (formData.password.length < 8) {
            alert('Please use a password with at least 8 characters');
            return;
        }

        const endpointUrl = 'http://localhost:8080/diamond-data/api/auth/sign-up';
        const url = new URL(endpointUrl);
        url.searchParams.append('teamName', formData.teamName);
        await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(body),
        })
        .then(response => response.json())
        .then(data => {
            localStorage.setItem('sessionData', JSON.stringify(data));
            if (data) {
                nav('/home', {
                    state: {
                        userSessionId: data
                    }
                })
            }
        })
        .catch(error => {
            alert('Account already exists, or passwords do not match');
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
                {/*<img src = {Logo} alt = "Logo" className = "center" />*/}
                <h1 className="title">
                    {/* <img src = {Logo} alt = "Logo" className = "center" /> */}
                    Diamond Data
                </h1>
            </div>

            <div className="signup-container">
                <h2>Sign Up</h2>
                <form className="signupForm" onSubmit={handleSubmit}>
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
                    <label>Team Name:
                        <input
                            type="text"
                            name="teamName"
                            value={formData.teamName}
                            onChange={handleChange}
                            required
                        />
                    </label>
                    <label>Name:
                        <input
                            type="text"
                            name="name"
                            value={formData.name}
                            onChange={handleChange}
                            required
                        />
                    </label>
                    <label>Phone number:
                        <input
                            type="text"
                            name="phoneNumber"
                            value={formData.phoneNumber}
                            onChange={handleChange}
                        />
                    </label>
                    <button type="submit">Sign Up</button>
                    <p>Already have an account? <a onClick={handleLogin}>Log in</a></p>
                </form>
            </div>
        </div>
    );
}

export default Signup;
