import { Link, useLocation } from "react-router-dom";

function NoDataPage() {
  const location = useLocation();
  const pageName = location.pathname.split("/").filter(Boolean)[0];

  return (
    <div>
      <div className="mx-40 mt-40">
        <span className="font-mono font-semibold text-7xl text-gray-600">
          No data on page {pageName.charAt(0).toUpperCase() + pageName.slice(1)}{" "}
        </span>
      </div>
      <div className="mx-40 my-10">
        <span className="font-mono font-semibold text-3xl text-gray-600">
          Oops.. The page you are in has nothing to display
        </span>
        <br /> <br />
        <span className="font-mono font-semibold text-2xl text-gray-600">
          You can click{" "}
          <Link className="underline text-blue-800" to="/">
            here
          </Link>{" "}
          to go back to the homepage
        </span>
      </div>
    </div>
  );
}

export default NoDataPage;
