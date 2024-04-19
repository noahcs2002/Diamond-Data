import React, { useState } from "react";
import '../styles/CreateAccount.scss';
import { ToastContainer, toast } from "react-toastify";

function CreateAccount({stateFunction, onDismiss}) {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleDismissal = () => {
        onDismiss(false);
    }

    return (
        <div>
            <ToastContainer/>
            {/* <Navbar/> */}
            <div className="createAccount-page">
                <div className="title-container">
                    <h1 className="title">Diamond Data</h1>
                </div>
                <div className="create-account-container">
                    <h2>Create Account</h2>
                    <form className="create-account-form">
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
                        <label>Re-enter Password:
                            <input
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            />
                        </label>
                        {/*Uses handleDismassal on Creat Account since backend is not connected.*/}
                        {/*<button onClick={handleCreateAccount}>Create Account</button>*/}
                        <button onClick={handleDismissal}>Create Account</button>
                        <p>Already have an account? <a onClick={handleDismissal}>Sign in</a></p>
                    </form>
                </div>
            </div>
        </div>
        
    );
}

export default CreateAccount;