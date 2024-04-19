import React, { useEffect, useState } from "react";
import '../styles/Login.scss';
import { useLocation, useNavigate } from "react-router-dom";
import { ToastContainer, toast, Bounce } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

function Login() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const nav = useNavigate();
    const location = useLocation();
    let toastId = React.useRef(null);

    useEffect(() => {
        localStorage.clear();
    }, [])

    const dismissToast = () => {
        toast.dismiss(toastId.current);
    }

    const handleLogin = async (e) => {
        toastId.current = toast('Signing in...', {
            position: 'bottom-right',
            autoClose:2500,
            hideProgressBar:true,
            closeOnClick:true,
            draggable:false,
        })

        e.preventDefault();

        const endpointUrl = 'http://localhost:8080/diamond-data/api/auth/login';

        const url = new URL(endpointUrl);
        url.searchParams.append('email', email);
        url.searchParams.append('password', password);

        const res = await fetch(url);
        try {
            const resData = await res.json();
            toast.success(`Welcome, ${resData.name}!`, {
                position:'bottom-right',
                autoClose: 1500,
                hideProgressBar:true,
                closeOnClick:true
            })
            localStorage.setItem('sessionData', JSON.stringify(resData));
            nav('/home', {
                state: {
                    name: resData.name
                }
            });
        }
        catch (error){
            dismissToast();
            toast.error('Invalid credentials', {
                position: "bottom-right",
                autoClose: 2500,
                hideProgressBar: true,
                closeOnClick: true,
                draggable: true,
                progress: undefined,
                theme: "light",
                transition: Bounce,
            })
        }
    };

    const handleSignUp = () => {
        nav('/sign-up');
    };

    return (
        <div className="login-page">
        <ToastContainer/>
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