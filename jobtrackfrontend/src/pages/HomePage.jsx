import { Link } from "react-router-dom";
import {
  Briefcase,
  BarChart3,
  Building2,
  ArrowRight,
} from "lucide-react";

export default function HomePage() {
  return (
    <div className="min-h-screen bg-slate-950 text-white">

      {/* Navbar */}
      <nav className="flex justify-between items-center px-10 py-5">
        <h1 className="text-3xl font-bold text-cyan-400">
          JobTrack
        </h1>

        <div className="flex gap-4">
          <Link
            to="/login"
            className="px-5 py-2 rounded-lg border border-slate-700 hover:border-cyan-400"
          >
            Login
          </Link>

          <Link
            to="/register"
            className="px-5 py-2 rounded-lg bg-cyan-500 hover:bg-cyan-600"
          >
            Register
          </Link>
        </div>
      </nav>

      {/* Hero */}

      <section className="max-w-7xl mx-auto px-10 py-24">

        <div className="grid md:grid-cols-2 items-center gap-10">

          <div>

            <div className="inline-block bg-cyan-500/20 text-cyan-300 px-4 py-2 rounded-full mb-6">
              Smart Job Application Management
            </div>

            <h1 className="text-6xl font-bold leading-tight">
              Track Every
              <span className="text-cyan-400"> Opportunity</span>
              <br />
              In One Place
            </h1>

            <p className="text-slate-400 mt-6 text-lg">
              Manage applications, interviews, salaries,
              follow-ups, and offers with a beautiful
              dashboard built for job seekers.
            </p>

            <div className="flex gap-4 mt-8">

              <Link
                to="/register"
                className="bg-cyan-500 px-8 py-4 rounded-xl flex items-center gap-2"
              >
                Start Tracking
                <ArrowRight size={18} />
              </Link>

              <Link
                to="/login"
                className="border border-slate-700 px-8 py-4 rounded-xl"
              >
                Login
              </Link>

            </div>

          </div>

          <div className="bg-white/5 backdrop-blur-md rounded-3xl p-8 border border-white/10">

            <div className="space-y-4">

              <div className="bg-slate-900 p-5 rounded-xl">
                <h3>Applications</h3>
                <p className="text-4xl font-bold text-cyan-400">124</p>
              </div>

              <div className="bg-slate-900 p-5 rounded-xl">
                <h3>Interviews</h3>
                <p className="text-4xl font-bold text-green-400">12</p>
              </div>

              <div className="bg-slate-900 p-5 rounded-xl">
                <h3>Offers</h3>
                <p className="text-4xl font-bold text-yellow-400">4</p>
              </div>

            </div>

          </div>

        </div>

      </section>

      {/* Features */}

      <section className="max-w-7xl mx-auto px-10 pb-20">
        <div className="grid md:grid-cols-3 gap-8">

          <div className="bg-white/5 p-7 rounded-xl">
            <Briefcase className="text-cyan-400 mb-4" />
            <h3 className="text-xl font-semibold">
              Manage Applications
            </h3>
            <p className="text-slate-400 mt-3">
              Store all applications with links,
              salary details and notes.
            </p>
          </div>

          <div className="bg-white/5 p-7 rounded-xl">
            <BarChart3 className="text-green-400 mb-4" />
            <h3 className="text-xl font-semibold">
              Application Analytics
            </h3>
            <p className="text-slate-400 mt-3">
              Monitor your job search progress
              through insightful dashboards.
            </p>
          </div>

          <div className="bg-white/5 p-7 rounded-xl">
            <Building2 className="text-yellow-400 mb-4" />
            <h3 className="text-xl font-semibold">
              Company Tracking
            </h3>
            <p className="text-slate-400 mt-3">
              Easily manage applications from
              multiple companies.
            </p>
          </div>

        </div>
      </section>
    </div>
  );
}
