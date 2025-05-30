import React, { useState } from 'react';
import '../styles/Signup.scss';
import { useNavigate } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';

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

        
        let body = {};
        body.email = formData.email;
        body.password = formData.password;
        body.name = formData.name;
        body.phoneNumber = formData.phoneNumber || '';

        if (formData.password !== formData.confirmPassword) {
            toast.error('Passwords don\'t match!', {
                position:'bottom-right',
                autoClose: 2500,
                hideProgressBar:true,
                closeOnClick:true 
            })
            return;
        }

        if (formData.password.length < 8) {
            toast.error('Please use a password longer than 8 characters', {
                position:'bottom-right',
                autoClose: 2500,
                hideProgressBar:true,
                closeOnClick:true 
            })
            return;
        }

        try {
            const endpointUrl = 'http://localhost:8080/diamond-data/api/auth/sign-up';
            const url = new URL(endpointUrl);
            url.searchParams.append('teamName', formData.teamName);
            const res = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(body),
            })

            const data = await res.json();
            localStorage.setItem('sessionData', JSON.stringify(data));
            toast.success('Signed up successfully! Welcome to Diamond Data!', {
                position:'bottom-right',
                autoClose: 2500,
                hideProgressBar:true,
                closeOnClick:true
            })
            nav('/home', {
                state: {
                    userSessionId: data,
                    name: data.name
                }
            });
        }
        catch {
            toast.error('Account already exists!', {
                position:'bottom-right',
                autoClose: 2500,
                hideProgressBar:true,
                closeOnClick:true 
            })
        }
       };

    const handleLogin = () => {
        
        nav('/login');
    };

    return (
        <div className="signup-page">
            <ToastContainer/>
            <div className="title-container">
                <h1 className="title">
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
