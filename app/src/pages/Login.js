import React, { useState } from "react";
import '../styles/Login.scss';

function Login({stateFunction, signUpFunction}) {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = () => {
            console.log('clicked');
            stateFunction(true);
    };

    const handleSignUp = () => {
        console.log('clicked');
        signUpFunction(true);
    };

    return (
        <div className="title-page">
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
                <form>
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
                    <button onClick={handleLogin}>Login</button>
                    <p>Don't have an account? <a onClick={handleSignUp}>Sign up</a></p>     {/* Adding a link to signup */}
                </form>
            </div>

            <footer>
                &copy; 2024 diamonddata.com     {/* Just creating a basic footer for the page. */}
            </footer>
        </div>
    );
}

export default Login;