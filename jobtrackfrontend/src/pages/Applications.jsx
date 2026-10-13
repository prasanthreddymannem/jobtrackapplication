import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";

export default function Applications() {
  const [applications, setApplications] = useState([]);
  const [loading, setLoading] = useState(true);

  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem("token");

    if (!token) {
      navigate("/login");
      return;
    }

    fetchApplications();
  }, []);

  const fetchApplications = async () => {
    try {
      const token = localStorage.getItem("token");

      const response = await axios.get(
        "http://localhost:8080/applications",
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      setApplications(response.data);
    } catch (error) {
      console.error(error);

      if (error.response?.status === 401) {
        localStorage.removeItem("token");
        navigate("/login");
      }
    } finally {
      setLoading(false);
    }
  };

  const logout = () => {
    localStorage.removeItem("token");
    navigate("/login");
  };

  const deleteApplication = async (id) => {
    try {
      const token = localStorage.getItem("token");

      await axios.delete(
        `http://localhost:8080/applications/${id}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      setApplications(
        applications.filter((app) => app.id !== id)
      );
    } catch (error) {
      console.error(error);
    }
  };

  if (loading) {
    return (
      <div className="min-h-screen bg-slate-950 text-white flex justify-center items-center">
        Loading...
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-slate-950 text-white p-8">
      <div className="flex justify-between items-center mb-8">
        <h1 className="text-4xl font-bold text-cyan-400">
          My Applications
        </h1>

        <div className="flex gap-3">
          <Link
            to="/applications/add"
            className="bg-cyan-500 px-4 py-2 rounded-lg"
          >
            Add Application
          </Link>

          <button
            onClick={logout}
            className="bg-red-500 px-4 py-2 rounded-lg"
          >
            Logout
          </button>
        </div>
      </div>

      {applications.length === 0 ? (
        <div className="bg-white/5 p-10 rounded-2xl text-center">
          No Applications Found
        </div>
      ) : (
        <div className="overflow-x-auto bg-white/5 rounded-2xl border border-white/10">

          <table className="w-full">

            <thead>
              <tr className="border-b border-slate-700">
                <th className="p-4 text-left">Company</th>
                <th className="p-4 text-left">Job Title</th>
                <th className="p-4 text-left">Location</th>
                <th className="p-4 text-left">Salary</th>
                <th className="p-4 text-left">Applied Date</th>
                <th className="p-4 text-left">Status</th>
                <th className="p-4 text-left">Actions</th>
              </tr>
            </thead>

            <tbody>

              {applications.map((app) => (
                <tr
                  key={app.id}
                  className="border-b border-slate-800 hover:bg-slate-900"
                >
                  <td className="p-4">{app.company}</td>

                  <td className="p-4">{app.jobTitle}</td>

                  <td className="p-4">{app.location}</td>

                  <td className="p-4">
                    ₹ {app.salary}
                  </td>

                  <td className="p-4">
                    {app.appliedDate}
                  </td>

                  <td className="p-4">
                    <span className="bg-cyan-500/20 text-cyan-300 px-3 py-1 rounded-lg">
                      {app.status}
                    </span>
                  </td>

                  <td className="p-4">
                    <button
                      onClick={() =>
                        deleteApplication(app.id)
                      }
                      className="bg-red-500 px-3 py-1 rounded"
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}

            </tbody>

          </table>
        </div>
      )}
    </div>
  );
}