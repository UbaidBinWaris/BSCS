<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
            position: relative;
            overflow: hidden;
        }

        /* Animated background circles */
        body::before,
        body::after {
            content: '';
            position: absolute;
            border-radius: 50%;
            background: rgba(255, 255, 255, 0.1);
            animation: float 6s ease-in-out infinite;
        }

        body::before {
            width: 300px;
            height: 300px;
            top: -100px;
            left: -100px;
        }

        body::after {
            width: 400px;
            height: 400px;
            bottom: -150px;
            right: -150px;
            animation-delay: 2s;
        }

        @keyframes float {
            0%, 100% { transform: translateY(0) rotate(0deg); }
            50% { transform: translateY(-20px) rotate(10deg); }
        }

        .login-container {
            background: white;
            border-radius: 25px;
            box-shadow: 0 30px 80px rgba(0, 0, 0, 0.35);
            padding: 60px 50px;
            max-width: 480px;
            width: 100%;
            animation: slideIn 0.6s ease-out;
            position: relative;
            z-index: 1;
        }

        @keyframes slideIn {
            from {
                opacity: 0;
                transform: translateY(-50px) scale(0.9);
            }
            to {
                opacity: 1;
                transform: translateY(0) scale(1);
            }
        }

        .logo {
            width: 80px;
            height: 80px;
            margin: 0 auto 25px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 40px;
            color: white;
            box-shadow: 0 10px 30px rgba(102, 126, 234, 0.4);
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 15px;
            font-size: 32px;
            font-weight: 700;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            background-clip: text;
        }

        .subtitle {
            text-align: center;
            color: #888;
            font-size: 15px;
            margin-bottom: 40px;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        .input-group {
            position: relative;
            margin-bottom: 25px;
        }

        .input-icon {
            position: absolute;
            left: 18px;
            top: 50%;
            transform: translateY(-50%);
            color: #999;
            font-size: 18px;
            transition: color 0.3s ease;
            pointer-events: none;
        }

        label {
            color: #555;
            font-weight: 600;
            margin-bottom: 10px;
            font-size: 14px;
            display: block;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        input {
            width: 100%;
            padding: 16px 20px 16px 50px;
            border: 2px solid #e8e8e8;
            border-radius: 12px;
            font-size: 16px;
            transition: all 0.3s ease;
            font-family: inherit;
            background: #f9f9f9;
        }

        input:focus {
            outline: none;
            border-color: #667eea;
            background: white;
            box-shadow: 0 0 0 4px rgba(102, 126, 234, 0.1);
        }

        input:focus ~ .input-icon {
            color: #667eea;
        }

        input::placeholder {
            color: #bbb;
        }

        .password-toggle {
            position: absolute;
            right: 18px;
            top: 50%;
            transform: translateY(-50%);
            color: #999;
            cursor: pointer;
            font-size: 18px;
            transition: color 0.3s ease;
        }

        .password-toggle:hover {
            color: #667eea;
        }

        button {
            width: 100%;
            padding: 16px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border: none;
            border-radius: 12px;
            font-size: 17px;
            font-weight: 700;
            cursor: pointer;
            transition: all 0.3s ease;
            margin-top: 15px;
            text-transform: uppercase;
            letter-spacing: 1px;
            box-shadow: 0 8px 20px rgba(102, 126, 234, 0.35);
        }

        button:hover {
            transform: translateY(-3px);
            box-shadow: 0 15px 35px rgba(102, 126, 234, 0.5);
        }

        button:active {
            transform: translateY(-1px);
        }

        .divider {
            text-align: center;
            margin: 30px 0;
            position: relative;
            color: #999;
            font-size: 14px;
        }

        .divider::before,
        .divider::after {
            content: '';
            position: absolute;
            top: 50%;
            width: 40%;
            height: 1px;
            background: #e8e8e8;
        }

        .divider::before {
            left: 0;
        }

        .divider::after {
            right: 0;
        }

        .footer-text {
            text-align: center;
            margin-top: 25px;
            color: #888;
            font-size: 14px;
        }

        .footer-text a {
            color: #667eea;
            text-decoration: none;
            font-weight: 600;
            transition: color 0.3s ease;
        }

        .footer-text a:hover {
            color: #764ba2;
            text-decoration: underline;
        }

        /* Responsive design */
        @media (max-width: 600px) {
            .login-container {
                padding: 40px 30px;
            }

            h2 {
                font-size: 26px;
            }
        }
    </style>
</head>
<body>

<div class="login-container">
    <div class="logo">🔐</div>
    <h2>Welcome Back</h2>
    <p class="subtitle">Please login to your account</p>

    <form action="auth">
        <div class="input-group">
            <label for="username">Username</label>
            <input id="username" name="username" placeholder="Enter your username" required>
            <span class="input-icon">👤</span>
        </div>

        <div class="input-group">
            <label for="password">Password</label>
            <input id="password" type="password" name="password" placeholder="Enter your password" required>
            <span class="input-icon">🔒</span>
            <span class="password-toggle">👁️</span>
        </div>

        <button type="submit">Login Now</button>
    </form>

    <div class="divider">or</div>
</div>

</body>
</html>

