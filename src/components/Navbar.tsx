import { NavLink, useLocation } from "react-router-dom";

function Navbar() {
  const location = useLocation();

  const getNavLinkClass = (basePath: string) => {
    const isActive = location.pathname.startsWith(basePath);
    return isActive
      ? "py-2 px-8 bg-green-200 pt-3 font-semibold text-gray-700 rounded-lg rounded-b-none mx-2"
      : "py-2 px-8 bg-white font-semibold text-gray-700 rounded-lg rounded-b-none mx-2";
  };

  return (
    <div className="flex justify-between w-full h-[100px] bg-[#4478B5] items-center">
      <span className="ml-12 text-white text-[32px] font-semibold">
        Artwork Shop
      </span>
      <nav className="mr-12">
        <NavLink to="/customers" className={() => getNavLinkClass("/customer")}>
          <span>Customers</span>
        </NavLink>
        <NavLink to="/events" className={() => getNavLinkClass("/events")}>
          <span>Events</span>
        </NavLink>
        <NavLink
          to="/my-account"
          className={() => getNavLinkClass("/my-account")}
        >
          <span>My Account</span>
        </NavLink>
      </nav>
    </div>
  );
}

export default Navbar;
