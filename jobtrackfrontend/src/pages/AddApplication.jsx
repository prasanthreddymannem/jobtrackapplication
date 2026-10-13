import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function AddApplication() {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    company: "",
    jobTitle: "",
    jobUrl: "",
    location: "",
    salary: "",
    appliedDate: "",
    status: "APPLIED",
    notes: "",
  });

  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      setLoading(true);

      const token = localStorage.getItem("token");

      await axios.post(
        "https://jobtrackapplication.onrender.com/applications",
        formData,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      navigate("/applications");
    } catch (error) {
      setMessage(
        error.response?.data?.message ||
          "Failed to create application"
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-slate-950 text-white p-8">

      <div className="max-w-3xl mx-auto">

        <h1 className="text-4xl font-bold text-cyan-400 mb-8">
          Add Job Application
        </h1>

        <div className="bg-white/5 border border-white/10 rounded-3xl p-8">

          {message && (
            <div className="bg-red-500/10 border border-red-500 p-3 rounded mb-5 text-red-400">
              {message}
            </div>
          )}

          <form
            onSubmit={handleSubmit}
            className="grid md:grid-cols-2 gap-5"
          >
            <input
              type="text"
              name="company"
              placeholder="Company"
              value={formData.company}
              onChange={handleChange}
              required
              className="bg-slate-900 p-3 rounded-xl"
            />

            <input
              type="text"
              name="jobTitle"
              placeholder="Job Title"
              value={formData.jobTitle}
              onChange={handleChange}
              required
              className="bg-slate-900 p-3 rounded-xl"
            />

            <input
              type="text"
              name="location"
              placeholder="Location"
              value={formData.location}
              onChange={handleChange}
              className="bg-slate-900 p-3 rounded-xl"
            />

            <input
              type="number"
              name="salary"
              placeholder="Salary"
              value={formData.salary}
              onChange={handleChange}
              className="bg-slate-900 p-3 rounded-xl"
            />

            <input
              type="url"
              name="jobUrl"
              placeholder="Job URL"
              value={formData.jobUrl}
              onChange={handleChange}
              className="bg-slate-900 p-3 rounded-xl col-span-2"
            />

            <input
  type="date"
  className="bg-slate-900 text-white p-3 rounded-xl"
  style={{ colorScheme: "dark" }}
/>

            <select
              name="status"
              value={formData.status}
              onChange={handleChange}
              className="bg-slate-900 p-3 rounded-xl"
            >
              <option value="APPLIED">APPLIED</option>
              <option value="INTERVIEW">INTERVIEW</option>
              <option value="OFFER">OFFER</option>
              <option value="REJECTED">REJECTED</option>
            </select>

            <textarea
              name="notes"
              placeholder="Notes"
              value={formData.notes}
              onChange={handleChange}
              rows="5"
              className="bg-slate-900 p-3 rounded-xl col-span-2"
            />

            <div className="col-span-2 flex gap-4">

              <button
                type="submit"
                disabled={loading}
                className="bg-cyan-500 px-8 py-3 rounded-xl font-semibold"
              >
                {loading
                  ? "Saving..."
                  : "Save Application"}
              </button>

              <button
                type="button"
                onClick={() => navigate("/applications")}
                className="border border-slate-600 px-8 py-3 rounded-xl"
              >
                Cancel
              </button>

            </div>

          </form>

        </div>

      </div>

    </div>
  );
}
