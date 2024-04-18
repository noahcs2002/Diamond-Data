import React, { useEffect, useState } from "react";
import '../styles/Login.scss';
import { useNavigate } from "react-router-dom";

function Login() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const nav = useNavigate();

    useEffect(() => {
        localStorage.clear();
    }, [])

    const handleLogin = async (e) => {

        e.preventDefault();

        const endpointUrl = 'http://localhost:8080/diamond-data/api/auth/login';

        const url = new URL(endpointUrl);
        url.searchParams.append('email', email);
        url.searchParams.append('password', password);

        await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        })
        .then(response => {
            if (!response.ok) {
                alert('Error signing in: credentials invalid');
            }
            return response.json();
        })
        .then(data => {
            if (data) {
                localStorage.setItem('sessionData', JSON.stringify(data))
                nav('/home', {
                    state: {
                        userSessionData: data
                    }
                })
            }
        })
        .catch(error => {
            console.log(error);
            alert('Invalid username or password')
        });
    };

    const handleDebug = () => {
    
    }

    const handleSignUp = () => {
        nav('/sign-up');
    };

    return (
        <div className="login-page">
            <div className="title-container">
                <h1 className="title">Diamond Data</h1>
            </div>

            {/* Email and Password boxes */}
            <div className="login-container">
                <h2>Login</h2>
                <form className="loginForm" onSubmit={handleLogin}>
                    <label>Email:
                        <input
                        type="text"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                    </label>
                    <label>Password:
                        <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                    </label>
                    <button onClick={handleLogin} type='button'>Login</button>
                    <p>Don't have an account? <a onClick={handleSignUp}>Sign up</a></p>     {/* Adding a link to signup */}
                </form>
            </div>
        </div>
    );
}

export default Login;