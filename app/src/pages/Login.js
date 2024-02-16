import React, { useState } from "react";
import '../styles/Login.scss';
import { useNavigate } from "react-router-dom";

function Login() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const nav = useNavigate();

    const handleLogin = async () => {

        // nav("/home");
        // console.log('hit')
        const endpointUrl = 'http://localhost:8080/diamond-data/api/auth/login';
        // let username = "root@dd-devs";
        // let password = "rootPassword1234$$";

        // Construct the URL with query parameters
        const url = new URL(endpointUrl);
        url.searchParams.append('email', email);
        url.searchParams.append('password', password);

        // Make the HTTP GET request
        await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                // Add any additional headers if needed
            },
        })
        // Start chaining responses
        // We take a response and move forward with it
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        // IF we have data, go to the home screen and transport that data with it
        .then(data => {
            if (data) {
                nav('/home', {
                    state: {
                        data: data
                    }
                })
            }
        })
        .catch(error => {
            alert('Invalid username or password')
        });
    };

    const handleDebug = () => {
    
    }

    const handleSignUp = () => {
        console.log('clicked');
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
                    <button onClick={handleLogin} type='button'>Login</button>
                    <p>Don't have an account? <a onClick={handleSignUp}>Sign up</a></p>     {/* Adding a link to signup */}
                </form>
            </div>
            <div>
                {/* <button onClick={() => {handleLogin("root@dd-devs", "rootPassword1234$$")} }>
                    click me
                </button> */}
            </div>

            <footer>
                &copy; 2024 diamonddata.com     {/* Just creating a basic footer for the page. */}
            </footer>
        </div>
    );
}

export default Login;