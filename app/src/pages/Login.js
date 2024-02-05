import React, { useState } from "react";
import '../styles/Login.scss';

function Login({stateFunction, signUpFunction}) {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = async () => {
        const endpointUrl = 'http://localhost:8080/diamond-data/api/auth/login';
        let username = "root@dd-devs";
        let password = "rootPassword1234$$";
        // Construct the URL with query parameters
        const url = new URL(endpointUrl);
        url.searchParams.append('username', username);
        url.searchParams.append('password', password);

        // Make the HTTP GET request
        fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                // Add any additional headers if needed
            },
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            // Log the result
            console.log(data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
    };

    const handleDebug = () => {
        stateFunction(true);
    }

    const handleSignUp = () => {
        console.log('clicked');
        signUpFunction(true);
    };

    return (
        <div className="login-page">
            <div className="title-container">
                {/*<img src = {Logo} alt = "Logo" className = "center" />*/}
                <h1 className="title">
                    {/* <img src = {Logo} alt = "Logo" className = "center" /> */}
                    Diamond Data
                </h1>
            </div>

            {/* Email and Password boxes */}
            <div className="login-container">
                <h2>Login</h2>
                <form className="loginForm">
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
                    <button onClick={handleDebug}>Login</button>
                    <p>Don't have an account? <a onClick={handleSignUp}>Sign up</a></p>     {/* Adding a link to signup */}
                </form>
            </div>
            <div>
                <button onClick={handleLogin}>
                    click me
                </button>
            </div>

            <footer>
                &copy; 2024 diamonddata.com     {/* Just creating a basic footer for the page. */}
            </footer>
        </div>
    );
}

export default Login;