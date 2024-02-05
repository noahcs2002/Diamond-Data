import React from 'react'
import '../styles/UserManagement.scss'
import Navbar from '../components/Navbar'

function UserManagement() {
  return (
    <div>
      <Navbar/>
      <div className="userManagement">
        <div className="leftSide">

        </div>
        <div className="rightSide">
            <h1> Form </h1>

            <form id="contact-form" method="POST" className='contactForm'>
                <label htmlFor="name">Full Name</label>
                <input name="name" placeholder="Enter full name.." type="text" />
                <label htmlFor="email">Email</label>
                <input name="email" placeholder="Enter email.." type="email" />
                <label htmlFor="message">Email</label>
                <textarea 
                rows="6"
                placeholder="Enter message..."
                name="message"
                required
                >
                </textarea>
                <button type="submit">Send</button>
            </form>
        </div>
      </div>
    </div> 
  )
}

export default UserManagement