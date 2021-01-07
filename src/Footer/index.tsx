import './style.css';
import {ReactComponent as InstagramIcons} from './Instagram.svg'; 
import {ReactComponent as LinkedinIcons} from './Linkedin.svg';
import {ReactComponent as YoutubeIcons} from './Youtube.svg';

function Footer() {
  return (
    <footer className="main-footer">
        App desenvolvido durante a 2ª ed. do evento Semana DevSuperior
        <div className="footer-icons">
        <a href="">
            <InstagramIcons />
        </a>
        <a href="">
            <LinkedinIcons/>
        </a>
        <a href="">
            <YoutubeIcons/>
        </a>
        </div>
    </footer>
  )
    
 
}

export default Footer;