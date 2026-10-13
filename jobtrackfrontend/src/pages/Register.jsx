import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Eye, EyeOff, User, Mail, Lock } from "lucide-react";
import axios from "axios";

export default function Register() {
  const navigate = useNavigate();

  const [showPassword, setShowPassword] = useState(false);

  const [loading, setLoading] = useState(false);

  const [message, setMessage] = useState("");

  const [formData, setFormData] = useState({
    name: "",
    email: "",
    password: "",
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    setMessage("");

    if (!formData.name.trim()) {
      return setMessage("Name is required");
    }

    if (!formData.email.trim()) {
      return setMessage("Email is required");
    }

    if (formData.password.length < 8) {
      return setMessage(
        "Password must be at least 8 characters"
      );
    }

    try {
      setLoading(true);

      await axios.post(
        "http://localhost:8080/auth/register",
        formData
      );

      setMessage("Registration Successful ✅");

      setTimeout(() => {
        navigate("/login");
      }, 1500);
    } catch (error) {
      setMessage(
        error.response?.data?.message ||
          "Registration Failed"
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-slate-950 flex justify-center items-center px-4">

      <div className="w-full max-w-md">

        <div className="text-center mb-8">
          <h1 className="text-4xl font-bold text-cyan-400">
            JobTrack
          </h1>

          <p className="text-slate-400 mt-2">
            Create your account and start tracking jobs
          </p>
        </div>

        <div className="backdrop-blur-lg bg-white/5 border border-white/10 rounded-3xl p-8 shadow-2xl">

          <h2 className="text-3xl font-bold text-white mb-6">
            Register
          </h2>

          {message && (
            <div className="mb-4 bg-slate-800 p-3 rounded-lg text-center text-white">
              {message}
            </div>
          )}

          <form
            onSubmit={handleSubmit}
            className="space-y-5"
          >
            {/* Name */}

            <div className="relative">
              <User
                className="absolute left-3 top-3.5 text-slate-400"
                size={18}
              />

              <input
                type="text"
                name="name"
                placeholder="Full Name"
                onChange={handleChange}
                className="w-full bg-slate-900 text-white rounded-xl py-3 pl-10 pr-4 outline-none border border-slate-700 focus:border-cyan-500"
                required
              />
            </div>

            {/* Email */}

            <div className="relative">
              <Mail
                className="absolute left-3 top-3.5 text-slate-400"
                size={18}
              />

              <input
                type="email"
                name="email"
                placeholder="Email Address"
                onChange={handleChange}
                className="w-full bg-slate-900 text-white rounded-xl py-3 pl-10 pr-4 outline-none border border-slate-700 focus:border-cyan-500"
                required
              />
            </div>

            {/* Password */}

            <div className="relative">
              <Lock
                className="absolute left-3 top-3.5 text-slate-400"
                size={18}
              />

              <input
                type={
                  showPassword
                    ? "text"
                    : "password"
                }
                name="password"
                placeholder="Password"
                onChange={handleChange}
                className="w-full bg-slate-900 text-white rounded-xl py-3 pl-10 pr-12 outline-none border border-slate-700 focus:border-cyan-500"
                required
              />

              <button
                type="button"
                onClick={() =>
                  setShowPassword(!showPassword)
                }
                className="absolute right-3 top-3"
              >
                {showPassword ? (
                  <EyeOff
                    className="text-slate-400"
                    size={20}
                  />
                ) : (
                  <Eye
                    className="text-slate-400"
                    size={20}
                  />
                )}
              </button>
            </div>

            <button
              disabled={loading}
              className="w-full bg-gradient-to-r from-cyan-500 to-blue-600 py-3 rounded-xl font-semibold hover:opacity-90 duration-300"
            >
              {loading
                ? "Creating Account..."
                : "Create Account"}
            </button>
          </form>

          <div className="mt-6 text-center text-slate-400">
            Already have an account?
            <Link
              to="/login"
              className="text-cyan-400 ml-2 hover:text-cyan-300"
            >
              Login
            </Link>
          </div>

        </div>
      </div>
    </div>
  );
}