import React, {useState} from 'react';
import * as FaIcons from 'react-icons/fa';
import * as AiIcons from 'react-icons/ai';
import { Link } from 'react-router-dom';
import { SidebarData } from './SidebarData';
import './../css/Navbar.css';
import { IconContext } from 'react-icons';
import { SubMenu } from './SubMenu';
import styled from 'styled-components';

function Navbar() {

    const [sidebar, setSidebar] = useState(false);

    const showSidebar = () => setSidebar(!sidebar);


    return (
        <>
            <IconContext.Provider value={{color: '#fff'}}>
                <div className='navbar'>
                    <Link to='#' className='menu-bars'>
                        <FaIcons.FaBars onClick={showSidebar} />
                    </Link>
                </div>
                <nav className={sidebar ? 'nav-menu active' : 'nav-menu'}>
                    <ul className='nav-menu-items' >

                        <Link to="#" className='menu-bars'>
                            <AiIcons.AiOutlineClose onClick={showSidebar}/>
                        </Link>

                        {SidebarData.map((item, index) => {
                        return <SubMenu item={item} key={index} />;
                        })}
                 </ul>
                </nav>
            </IconContext.Provider>
        </>
    );
};

export default Navbar;